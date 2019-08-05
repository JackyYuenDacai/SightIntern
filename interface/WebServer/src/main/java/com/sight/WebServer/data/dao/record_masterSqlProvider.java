package com.sight.WebServer.data.dao;

import com.sight.WebServer.data.model.record_master;
import com.sight.WebServer.data.model.record_masterExample.Criteria;
import com.sight.WebServer.data.model.record_masterExample.Criterion;
import com.sight.WebServer.data.model.record_masterExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class record_masterSqlProvider {

    public String countByExample(record_masterExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("record_master");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(record_masterExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("record_master");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(record_master record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("record_master");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.VALUES("location", "#{location,jdbcType=VARCHAR}");
        }
        
        if (record.getRecordIn() != null) {
            sql.VALUES("record_in", "#{recordIn,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRecordOut() != null) {
            sql.VALUES("record_out", "#{recordOut,jdbcType=TIMESTAMP}");
        }
        
        if (record.getData() != null) {
            sql.VALUES("data", "#{data,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.VALUES("token", "#{token,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(record_masterExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("type");
        sql.SELECT("location");
        sql.SELECT("record_in");
        sql.SELECT("record_out");
        sql.SELECT("data");
        sql.SELECT("token");
        sql.FROM("record_master");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        record_master record = (record_master) parameter.get("record");
        record_masterExample example = (record_masterExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("record_master");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.SET("location = #{record.location,jdbcType=VARCHAR}");
        }
        
        if (record.getRecordIn() != null) {
            sql.SET("record_in = #{record.recordIn,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRecordOut() != null) {
            sql.SET("record_out = #{record.recordOut,jdbcType=TIMESTAMP}");
        }
        
        if (record.getData() != null) {
            sql.SET("data = #{record.data,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("record_master");
        
        sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        sql.SET("location = #{record.location,jdbcType=VARCHAR}");
        sql.SET("record_in = #{record.recordIn,jdbcType=TIMESTAMP}");
        sql.SET("record_out = #{record.recordOut,jdbcType=TIMESTAMP}");
        sql.SET("data = #{record.data,jdbcType=VARCHAR}");
        sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        
        record_masterExample example = (record_masterExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(record_master record) {
        SQL sql = new SQL();
        sql.UPDATE("record_master");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.SET("location = #{location,jdbcType=VARCHAR}");
        }
        
        if (record.getRecordIn() != null) {
            sql.SET("record_in = #{recordIn,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRecordOut() != null) {
            sql.SET("record_out = #{recordOut,jdbcType=TIMESTAMP}");
        }
        
        if (record.getData() != null) {
            sql.SET("data = #{data,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{token,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, record_masterExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}