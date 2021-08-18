package com.ssafy.api.service;

import com.ssafy.db.entity.Billing;
import com.ssafy.db.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    BillingRepository billingRepository;

    @Override
    public Billing createBill(String orderInfoId) {
        Billing billing = new Billing();
        billing.setOrderInfoId(orderInfoId);
        billing.setBillingStatus("Success");
        return billingRepository.save(billing);
    }
}
