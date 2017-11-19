package com.epam.lab.payments.domain;

import com.epam.lab.payments.auditor.Action;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@Table(name = "user_audit", schema = "public")
public class UserHistoryEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;

    @CreatedBy
    @Basic
    @Column(name = "modified_by", nullable = false, length = -1)
    private String modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Basic
    @Column(name = "modified_date", nullable = false, length = -1)
    private Date modifiedDate;

    @Enumerated(STRING)
    @Basic
    @Column(name = "action", nullable = false, length = -1)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_audit_user_id_fk"))
    private UserEntity userEntity;

    public UserHistoryEntity(UserEntity userEntity, Action action) {
        this.userEntity = userEntity;
        this.action = action;
    }

}
