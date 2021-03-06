/*
 * This file is part of a module with proprietary Enterprise Features.
 *
 * Licensed to Crate.io Inc. ("Crate.io") under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * To use this file, Crate.io must have given you permission to enable and
 * use such Enterprise Features and you must have a valid Enterprise or
 * Subscription Agreement with Crate.io.  If you enable or use the Enterprise
 * Features, you represent and warrant that you have a valid Enterprise or
 * Subscription Agreement with Crate.io.  Your use of the Enterprise Features
 * if governed by the terms and conditions of your Enterprise or Subscription
 * Agreement with Crate.io.
 */

package io.crate.beans;

import org.elasticsearch.common.breaker.CircuitBreaker;
import org.elasticsearch.indices.breaker.CircuitBreakerService;
import org.elasticsearch.indices.breaker.CircuitBreakerStats;
import org.elasticsearch.indices.breaker.HierarchyCircuitBreakerService;

public class CircuitBreakers implements CircuitBreakersMXBean {

    public static final String NAME = "io.crate.monitoring:type=CircuitBreakers";

    private final CircuitBreakerService circuitBreakerService;
    private final CircuitBreakerStats EMPTY_FIELDDATA_STATS = new CircuitBreakerStats("fielddata", -1, -1, 0, 0);

    public CircuitBreakers(CircuitBreakerService circuitBreakerService) {
        this.circuitBreakerService = circuitBreakerService;
    }

    @Override
    public CircuitBreakerStats getParent() {
        return circuitBreakerService.stats(CircuitBreaker.PARENT);
    }

    @Override
    public CircuitBreakerStats getFieldData() {
        return EMPTY_FIELDDATA_STATS;
    }

    @Override
    public CircuitBreakerStats getInFlightRequests() {
        return circuitBreakerService.stats(CircuitBreaker.IN_FLIGHT_REQUESTS);
    }

    @Override
    public CircuitBreakerStats getRequest() {
        return circuitBreakerService.stats(CircuitBreaker.REQUEST);
    }

    @Override
    public CircuitBreakerStats getQuery() {
        return circuitBreakerService.stats(HierarchyCircuitBreakerService.QUERY);
    }

    @Override
    public CircuitBreakerStats getJobsLog() {
        return circuitBreakerService.stats(HierarchyCircuitBreakerService.JOBS_LOG);
    }

    @Override
    public CircuitBreakerStats getOperationsLog() {
        return circuitBreakerService.stats(HierarchyCircuitBreakerService.OPERATIONS_LOG);
    }
}
