package com.springboot.student.spring_example_students.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class BucketDTO {

    private int amountStudents;

    private List<BucketDetailsDTO> bucketDetails = new ArrayList<>();

    public void aggregate() {
        this.amountStudents = bucketDetails.size();
    }

    public BucketDTO() {
    }

    public BucketDTO(int amountStudents, List<BucketDetailsDTO> bucketDetails) {
        this.amountStudents = amountStudents;
        this.bucketDetails = bucketDetails;
    }

    public int getAmountStudents() {
        return amountStudents;
    }

    public void setAmountStudents(int amountStudents) {
        this.amountStudents = amountStudents;
    }

    public List<BucketDetailsDTO> getBucketDetails() {
        return bucketDetails;
    }

    public void setBucketDetails(List<BucketDetailsDTO> bucketDetails) {
        this.bucketDetails = bucketDetails;
    }

    @Override
    public String toString() {
        return "BucketDTO{" +
                "amountStudents=" + amountStudents +
                ", bucketDetails=" + bucketDetails +
                '}';
    }
}
