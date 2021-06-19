package com.example.actvn.repository.qrinfo;

import com.example.actvn.entity.QrInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRInfoRepository extends JpaRepository<QrInfo, Long> {
}
