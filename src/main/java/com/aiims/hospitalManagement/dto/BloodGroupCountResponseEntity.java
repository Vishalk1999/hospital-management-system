package com.aiims.hospitalManagement.dto;

import com.aiims.hospitalManagement.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BloodGroupCountResponseEntity {
    private BloodGroupType bloodGroupType;
    private Long count;

}
