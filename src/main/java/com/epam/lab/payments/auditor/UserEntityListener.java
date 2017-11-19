package com.epam.lab.payments.auditor;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.domain.UserHistoryEntity;
import com.epam.lab.payments.services.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.epam.lab.payments.auditor.Action.DELETED;
import static com.epam.lab.payments.auditor.Action.INSERTED;
import static com.epam.lab.payments.auditor.Action.UPDATED;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class UserEntityListener {
    @PrePersist
    public void prePersist(UserEntity target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(UserEntity target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(UserEntity target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    void perform(UserEntity target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new UserHistoryEntity(target, action));
    }
}
