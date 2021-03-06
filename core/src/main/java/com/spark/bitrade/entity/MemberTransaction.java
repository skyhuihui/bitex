package com.spark.bitrade.entity;

import com.spark.bitrade.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spark.bitrade.constant.TransactionType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @desc 会员交易记录，包括充值、提现、转账等
 *
 */
@Entity
@Data
public class MemberTransaction {
    @Excel(name = "交易记录编号")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Excel(name = "会员id")
    private Long memberId;
    /**
     * 交易金额
     */
    @Excel(name = "交易金额")
    @Column(columnDefinition = "decimal(18,8) comment '充币金额'")
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 交易类型
     */
    @Enumerated(EnumType.ORDINAL)
    private TransactionType type;
    /**
     * 币种名称，如 BTC
     */
    private String symbol;
    /**
     * 充值或提现地址、或转账地址
     */
    private String address;

    /**
     * 交易手续费
     * 提现和转账才有手续费，充值没有;如果是法币交易，只收发布广告的那一方的手续费
     */
    @Column(precision = 19,scale = 8)
    private BigDecimal fee = BigDecimal.ZERO ;

    /**
     * 标识位，特殊情况会用到，默认为0
     */
    @Column(nullable=false,columnDefinition="int default 0")
    private int flag = 0;
    /**
     * 空投ID，只有空投时才有
     */
    private Long airdropId;

    @Excel(name = "交易类型")
    @Transient
    private String typeCN;
}
