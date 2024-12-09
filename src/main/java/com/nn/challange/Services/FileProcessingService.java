package com.nn.challange.Services;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nn.challange.Entities.Policy;
import com.nn.challange.Entities.PayOut;
import com.nn.challange.Repositories.PolicyRepository;
import com.nn.challange.Repositories.PayOutRepository;
import com.nn.challange.Utils.FileReaderUtil;

@Service
public class FileProcessingService {
    private final PayOutRepository payOutRepository;
    private final PolicyRepository policyRepository;
    private final RedemptionProcessingService redemptionDataService;
    private static final Logger logger = Logger.getLogger(FileProcessingService.class.getName());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FileProcessingService(PayOutRepository payOutRepository, PolicyRepository policyRepository, RedemptionProcessingService redemptionDataService) {
        this.redemptionDataService = redemptionDataService;
        this.payOutRepository = payOutRepository;
        this.policyRepository = policyRepository;
    }

    public void processFiles() throws IOException {
        processRedemptionDataFile("files/ZTPSPF.TXT");
        processOutPayDataFile("files/OUTPH_CUP_20200204_1829.TXT", ";");
        processPolicyDataFile("files/CUSTCOMP01.txt", "\\|");
    }

    private void processRedemptionDataFile(String filePath) throws IOException {
        redemptionDataService.processRedemptionDataFile(filePath);
    }

    private void processOutPayDataFile(String filePath, String delimiter) throws IOException {
        List<String[]> records = FileReaderUtil.readFile(filePath, delimiter);
        for (String[] fields : records) {
                PayOut payOut = new PayOut();
                payOut.setClntnum(fields[0]);
                payOut.setChdrnum(fields[1]);
                payOut.setLetterType(fields[2]);

                LocalDate printDate = LocalDate.parse(fields[3], formatter);
                payOut.setPrintDate(printDate.atTime(LocalTime.MIDNIGHT));
                LocalDate regDate = LocalDate.parse(fields[3], formatter);
                payOut.setRegDate(regDate.atTime(LocalTime.MIDNIGHT));

                payOut.setDataID(fields[4]);
                payOut.setClntName(fields[5]);
                payOut.setClntAddress(fields[6]);
                payOut.setBenPercent(new BigDecimal(fields[8]));
                payOut.setRole1(fields[9]);
                payOut.setRole2(fields[10]);
                payOut.setCownNum(fields[11]);
                payOut.setCownName(fields[12]);

                payOut.setNotice01(null);
                payOut.setNotice02(null);
                payOut.setNotice03(null);
                payOut.setNotice04(null);
                payOut.setNotice05(null);
                payOut.setNotice06(null);
                payOut.setClaimID(null);
                payOut.setTp2ProcessDate(null);

            try {
                payOutRepository.save(payOut);
            } catch (DataIntegrityViolationException e) {
                logger.warning("Failed to save payOut record due to data length violation: " + e.getMessage());
            } catch (Exception e) {
                logger.warning("Failed to process record: " + Arrays.toString(fields) + " Error: " + e.getMessage());
            }
        }
    }

    private void processPolicyDataFile(String filePath, String delimiter) throws IOException {
        List<String[]> records = FileReaderUtil.readFile(filePath, delimiter);
        for (String[] fields : records) {
                System.out.println(Arrays.toString(fields));
                Policy policy = new Policy();
                policy.setChdrnum(fields[0]);
                policy.setCownnum(fields[1]);
                policy.setOwnerName(fields[2]);
                policy.setLifcNum(fields[3]);
                policy.setLifcName(fields[4]);
                policy.setAracde(fields[5]);
                policy.setAgntnum(fields[6]);
                policy.setMailAddress(fields[7]);
            try {
                policyRepository.save(policy);
            } catch (DataIntegrityViolationException e) {
                logger.warning("Failed to save policy record due to data length violation: " + e.getMessage());
            } catch (Exception e) {
                logger.warning("Failed to process policy record: " + Arrays.toString(fields) + " Error: " + e.getMessage());
            }
        }
    }
}
