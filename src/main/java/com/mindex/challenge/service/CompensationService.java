package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * the compensatio api being exposed
 */
public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String id);
}
