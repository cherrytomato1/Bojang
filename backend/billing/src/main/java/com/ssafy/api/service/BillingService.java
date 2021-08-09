package com.ssafy.api.service;

import com.ssafy.db.entity.Billing;

public interface BillingService {
    Billing createBill(String orderInfoId);
}
