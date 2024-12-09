package com.nn.challange.Services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nn.challange.Entities.Redemption;
import com.nn.challange.Repositories.RedemptionRepository;
import com.nn.challange.Utils.FixedWidthFileReader;

@Service
public class RedemptionProcessingService {
    
    private final RedemptionRepository redemptionRepository;

    public RedemptionProcessingService(RedemptionRepository redemptionRepository) {
        this.redemptionRepository = redemptionRepository;
    }

    @Transactional
    public void processRedemptionDataFile(String filePath) throws IOException {
        // Define field lengths based on the fixed-width mapping
        int[] fieldLengths = {1, 8, 15, 20, 10, 26};

        // Read the file and parse the fields
        List<String[]> records = FixedWidthFileReader.parseFixedWidthFile(filePath, fieldLengths);

        for (String[] fields : records) {
            Redemption redemption = new Redemption();
        
            redemption.setCompany(fields[0]); 
            redemption.setChdrnum(fields[1]);
            redemption.setSurvalue(new BigDecimal(fields[2]));
            redemption.setCurrency("HUF");
            redemption.setValidDate(fields[4]);
        
            redemptionRepository.save(redemption);
        }
        
    }
}

