package com.example.springboothibernatevalidator.entity;

/**
 * 在对象参数校验场景下，有一种特殊场景，同一个参数对象在不同的场景下有不同的校验规则。
 * 比如，在创建对象时不需要传入id字段（id字段是主键，由系统生成，不由用户指定），
 * 但是在修改对象时就必须要传入id字段。在这样的场景下就需要对注解进行分组
 * 创建一个分组UpdateAction.class
 */
public interface UpdateAction {
}
