package com.ai.medicalAgent.mapper;

import com.ai.medicalAgent.entity.Appointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
