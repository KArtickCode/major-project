package com.example.major_project.services;

import com.example.major_project.models.Wallet;
import com.example.major_project.repositories.WalletRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    private static Logger logger = LoggerFactory.getLogger(WalletService.class);

    private JSONParser jsonParser = new JSONParser();

    private static final String USER_CREATED_TOPIC = "user-created";
    private static final String Transaction_CREATED_TOPIC = "transaction-created";
    private static final String WALLET_UPDATED_TOPIC = "wallet-updated";

    @Value("${wallet.opening-balance}")
    private Long walletOpeningBalance;

    private String currency = "INR";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;


    @KafkaListener(topics = USER_CREATED_TOPIC, groupId = "wallet_group")
    public void createWallet(String msg) throws ParseException {

        JSONObject data = (JSONObject) jsonParser.parse(msg);

        Long id = (Long) data.get("id");
        String userId = String.valueOf(id);

        Wallet wallet = walletRepository.findByUserId(userId);

        if(wallet != null){
            logger.warn("wallet already exists for {} ", userId);
//            wallet.setBalance(wallet.getBalance() + walletOpeningBalance);
            return;
        }

        wallet = Wallet.builder()
                .userId(userId)
                .balance(walletOpeningBalance)
                .currency(currency)
                .build();

        this.walletRepository.save(wallet);

        logger.info("Wallet created for user {}", userId);

    }

    @KafkaListener(topics = Transaction_CREATED_TOPIC, groupId = "wallet_group")
    public void updateWalletsForTransaction(String msg) throws ParseException, JsonProcessingException {

        JSONObject data = (JSONObject) jsonParser.parse(msg);

        String sender = (String) data.get("sender");
        String receiver = (String) data.get("receiver");
        Long amount = (Long) (data.get("amount"));

        String externalTransactionId = (String) data.get("externalTransactionId");



        Wallet senderWallet = walletRepository.findByUserId(String.valueOf(sender));
        if(senderWallet == null || senderWallet.getBalance() == null || senderWallet.getBalance() < amount){
            logger.warn("Sender does not have sufficient balance to send money ");
            // TODO: Publish wallet update failure events
            JSONObject walletEventData = constructFailedEvent(externalTransactionId, sender, receiver, amount);
            this.kafkaTemplate.send(WALLET_UPDATED_TOPIC,objectMapper.writeValueAsString(walletEventData));

            return;
        }

        Wallet receiverWallet = walletRepository.findByUserId(String.valueOf(receiver));
        if(receiverWallet == null){
            logger.warn("Receiver wallet does not exist ");
            // TODO : Publish failure events
            JSONObject walletEventData = constructFailedEvent(externalTransactionId, sender, receiver, amount);
            this.kafkaTemplate.send(WALLET_UPDATED_TOPIC,objectMapper.writeValueAsString(walletEventData));

            return;
        }

//        senderWallet.setBalance(senderWallet.getBalance() - amount);
//        receiverWallet.setBalance(receiverWallet.getBalance() + amount);
//        walletRepository.saveAll(Arrays.asList(senderWallet, receiverWallet));

        // above three lines can be replaced with below two lines
        walletRepository.updateWallet(senderWallet.getId(), -amount);     // sender sends money so amount is negative
        walletRepository.updateWallet(receiverWallet.getId(), amount);    // receiver receives money so amount is positive

        JSONObject walletEventData = constructSuccessEvent(externalTransactionId, sender, receiver, amount,
                senderWallet.getId(), receiverWallet.getId());
        this.kafkaTemplate.send(WALLET_UPDATED_TOPIC,objectMapper.writeValueAsString(walletEventData));

    }

    private JSONObject constructFailedEvent(String externalTransactionId, String sender, String receiver, Long amount) {
        JSONObject walletEventData = new JSONObject();
        walletEventData.put("externalTransactionId", externalTransactionId);
        walletEventData.put("sender", sender);  // user id
        walletEventData.put("receiver", receiver);  // user id
        walletEventData.put("amount", amount);
        walletEventData.put("status", "FAILED");

        return walletEventData;
    }

    private JSONObject constructSuccessEvent(String externalTransactionId, String sender, String receiver, Long amount,
                                            Long senderWalletId, Long receiverWalletId) {
        JSONObject walletEventData = new JSONObject();
        walletEventData.put("externalTransactionId", externalTransactionId);
        walletEventData.put("sender", sender);  // user id
        walletEventData.put("receiver", receiver);  // user id
        walletEventData.put("amount", amount);
        walletEventData.put("status", "SUCCESS");
        walletEventData.put("senderWalletId", senderWalletId);      // wallet id
        walletEventData.put("receiverWalletId", receiverWalletId);  // wallet id

        return walletEventData;
    }


}



