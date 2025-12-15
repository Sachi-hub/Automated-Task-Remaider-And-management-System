//package com.mywaysai.automatedtaskremindertrackingapplication.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.mywaysai.automatedtaskremindertrackingapplication.entity.NotificationEntity;
//
//public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
//    List<NotificationEntity> findByUserIdAndReadFlagFalse(Long userId);
//}