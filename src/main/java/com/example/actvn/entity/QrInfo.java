package com.example.actvn.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "qr_info")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QrInfo {
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "time_began_qrcode", nullable = false)
    private Timestamp timeBeganQrcode;
    @Basic
    @Column(name = "qrcode_end_time", nullable = false)
    private Timestamp qrcodeEndTime;
    @Basic
    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;
    @Basic
    @Column(name = "latitude", nullable = false)
    private Long latitude; // kinh độ
    @Basic
    @Column(name = "longitude", nullable = false)
    private Long longitude ; // vĩ độ
    @Basic
    @Column(name = "is_active", nullable = false, insertable = false)
    private Integer isActive;

}
