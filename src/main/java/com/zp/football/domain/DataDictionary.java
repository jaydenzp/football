package com.zp.football.domain;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/30 9:29
 */

/**
 * , indexes = {
 *         @Index(name = "in_code",  columnList="code"),
 *         @Index(name = "in_value",  columnList="value"),
 *         @Index(name = "in_date_type",  columnList="dateType")}
 */

@Entity
@Table(name = "data_dic")
public class DataDictionary {

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parentCode;

    private String dateType;

    private String code;

    private String value;

    private String description;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "DataDictionary{" +
                "id=" + id +
                ", parentCode='" + parentCode + '\'' +
                ", dateType='" + dateType + '\'' +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
