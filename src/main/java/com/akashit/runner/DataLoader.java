package com.akashit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.akashit.entity.CitizenPlan;
import com.akashit.repo.CitizenRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        citizenRepository.deleteAll();

        // Generate data
        List<CitizenPlan> plans = Arrays.asList(
            // Cash Plan Data - Approved
            createCitizenPlan("John", "Male", "Cash", "Approved", 10000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Emily", "Female", "Cash", "Approved", 10000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Michael", "Male", "Cash", "Approved", 10000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Sarah", "Female", "Cash", "Approved", 10000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            
            // Cash Plan Data - Denied
            createCitizenPlan("Jane", "Female", "Cash", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Eligibility criteria not met"),
            createCitizenPlan("Robert", "Male", "Cash", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Eligibility criteria not met"),
            createCitizenPlan("Linda", "Female", "Cash", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Eligibility criteria not met"),
            createCitizenPlan("David", "Male", "Cash", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Eligibility criteria not met"),

            // Cash Plan Data - Terminated
            createCitizenPlan("Doe", "Male", "Cash", "Terminated", 5000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Violation of policy terms"),
            createCitizenPlan("Olivia", "Female", "Cash", "Terminated", 5000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Violation of policy terms"),
            createCitizenPlan("Ethan", "Male", "Cash", "Terminated", 5000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Violation of policy terms"),
            createCitizenPlan("Sophia", "Female", "Cash", "Terminated", 5000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Violation of policy terms"),

            // Employment Plan Data - Approved
            createCitizenPlan("Alice", "Female", "Employment", "Approved", 15000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("James", "Male", "Employment", "Approved", 15000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Lily", "Female", "Employment", "Approved", 15000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Daniel", "Male", "Employment", "Approved", 15000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),

            // Employment Plan Data - Denied
            createCitizenPlan("Bob", "Male", "Employment", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Inadequate documentation"),
            createCitizenPlan("Mia", "Female", "Employment", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Inadequate documentation"),
            createCitizenPlan("Oliver", "Male", "Employment", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Inadequate documentation"),
            createCitizenPlan("Ava", "Female", "Employment", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Inadequate documentation"),

            // Employment Plan Data - Terminated
            createCitizenPlan("Eve", "Female", "Employment", "Terminated", 8000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Policy violation"),
            createCitizenPlan("Liam", "Male", "Employment", "Terminated", 8000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Policy violation"),
            createCitizenPlan("Emma", "Female", "Employment", "Terminated", 8000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Policy violation"),
            createCitizenPlan("Noah", "Male", "Employment", "Terminated", 8000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Policy violation"),

            // Food Plan Data - Approved
            createCitizenPlan("Charlie", "Male", "Food", "Approved", 2000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Ella", "Female", "Food", "Approved", 2000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Lucas", "Male", "Food", "Approved", 2000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Avery", "Female", "Food", "Approved", 2000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),

            // Food Plan Data - Denied
            createCitizenPlan("Daisy", "Female", "Food", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Insufficient need"),
            createCitizenPlan("Henry", "Male", "Food", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Insufficient need"),
            createCitizenPlan("Mason", "Male", "Food", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Insufficient need"),
            createCitizenPlan("Isabella", "Female", "Food", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Insufficient need"),

            // Food Plan Data - Terminated
            createCitizenPlan("Frank", "Male", "Food", "Terminated", 1000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Non-compliance with guidelines"),
            createCitizenPlan("Sophia", "Female", "Food", "Terminated", 1000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Non-compliance with guidelines"),
            createCitizenPlan("Ethan", "Male", "Food", "Terminated", 1000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Non-compliance with guidelines"),
            createCitizenPlan("Olivia", "Female", "Food", "Terminated", 1000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Non-compliance with guidelines"),

            // Medical Plan Data - Approved
            createCitizenPlan("Grace", "Female", "Medical", "Approved", 20000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Jack", "Male", "Medical", "Approved", 20000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Mia", "Female", "Medical", "Approved", 20000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),
            createCitizenPlan("Oliver", "Male", "Medical", "Approved", 20000.0, LocalDate.now(), LocalDate.now().plusMonths(6)),

            // Medical Plan Data - Denied
            createCitizenPlan("Henry", "Male", "Medical", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Pre-existing conditions not covered"),
            createCitizenPlan("Emily", "Female", "Medical", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Pre-existing conditions not covered"),
            createCitizenPlan("Jacob", "Male", "Medical", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Pre-existing conditions not covered"),
            createCitizenPlan("Ava", "Female", "Medical", "Denied", 0.0, LocalDate.now(), LocalDate.now().plusMonths(6), "Pre-existing conditions not covered"),

            // Medical Plan Data - Terminated
            createCitizenPlan("Ivy", "Female", "Medical", "Terminated", 10000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Failure to adhere to treatment protocols"),
            createCitizenPlan("Liam", "Male", "Medical", "Terminated", 10000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Failure to adhere to treatment protocols"),
            createCitizenPlan("Mason", "Male", "Medical", "Terminated", 10000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Failure to adhere to treatment protocols"),
            createCitizenPlan("Ella", "Female", "Medical", "Terminated", 10000.0, LocalDate.now().minusMonths(6), LocalDate.now(), "Failure to adhere to treatment protocols")
        );

        // Save records to the database
        List<CitizenPlan> savedPlans = citizenRepository.saveAll(plans);
        System.out.println(savedPlans);
    }

    private CitizenPlan createCitizenPlan(String name, String gender, String planName, String planStatus, double benefitAmt, LocalDate startDate, LocalDate endDate) {
        return createCitizenPlan(name, gender, planName, planStatus, benefitAmt, startDate, endDate, null);
    }

    private CitizenPlan createCitizenPlan(String name, String gender, String planName, String planStatus, double benefitAmt, LocalDate startDate, LocalDate endDate, String reason) {
        CitizenPlan plan = new CitizenPlan();
        plan.setCitizenName(name);
        plan.setGender(gender);
        plan.setPlanName(planName);
        plan.setPlanStatus(planStatus);
        plan.setPlanStartDate(startDate);
        plan.setPlanEndDate(endDate);
        plan.setBenefitAmt(benefitAmt);
        if (reason != null) {
            if (planStatus.equals("Denied")) {
                plan.setDenialReason(reason);
            } else if (planStatus.equals("Terminated")) {
                plan.setTerminationRsn(reason);
            }
        }
        return plan;
    }
}
