package com.dh.g1.apicard.feign;

import com.dh.g1.apicard.feign.model.Concept;
import com.dh.g1.apicard.feign.model.CustomerCategory;
import com.dh.g1.apicard.feign.model.EconomicActivity;
import com.dh.g1.apicard.feign.model.RiskLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name="api-margins")
public interface IMarginServiceClient {

    @GetMapping("/api/v1/margins/calculate/{documentType}/{documentValue}")
    Calification calculateCalification(@PathVariable String documentType, @PathVariable String documentValue);


    @Getter
    @Setter
    class Calification {

        private String documentType;
        private String documentValue;
        private BigDecimal totalMargin;
        private BigDecimal totalMarginAdditional;
        private EconomicActivity economicActivity;
        private RiskLevel riskLevel;
        private CustomerCategory customerCategory;
        private List<Sublimit> sublimits;
        @Getter
        @Setter
        public static class Sublimit{
            private Concept concept;
            private BigDecimal totalMargin;
        }
    }
}
