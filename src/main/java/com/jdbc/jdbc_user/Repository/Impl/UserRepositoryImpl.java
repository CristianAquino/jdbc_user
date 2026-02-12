package com.jdbc.jdbc_user.Repository.Impl;

import com.jdbc.jdbc_user.Model.Request.UserRequest;
import com.jdbc.jdbc_user.Model.UserModel;
import com.jdbc.jdbc_user.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall getAllUsers;
    private SimpleJdbcCall getOneUserById;
    private SimpleJdbcCall postUserRegister;
    private SimpleJdbcCall delUserById;

    @Override
    public List<UserModel> getAllUsers(Integer is_soft) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("IS_SOFT",is_soft);
        Map<String,Object> out = getAllUsers.execute(in);
        List<UserModel> users = (List<UserModel>) out.get("POC_CURSOR");
        return users;
    }

    @Override
    public UserModel getOneUserById(Integer id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("PON_ID",id);
        Map<String,Object> out = getOneUserById.execute(in);
        UserModel user = ((List<UserModel>) out.get("POC_CURSOR")).get(0);
        return user;
    }

    @Override
    public UserModel postUserRegister(UserRequest user) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("POV_USERNAME",user.getUsername())
                .addValue("POV_PASSWORD",user.getContrasena())
                .addValue("POV_FIRSTNAME",user.getAp_materno())
                .addValue("POV_LASTNAME",user.getAp_materno())
                .addValue("POV_COUNTRY",user.getPais());
        Map<String,Object> out = postUserRegister.execute(in);
        UserModel register = ((List<UserModel>) out.get("POC_CURSOR")).get(0);
        return register;
    }

    @Override
    public UserModel delUserById(Integer id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("PON_ID",id);
        Map<String,Object> out = delUserById.execute(in);
        UserModel userDelete = ((List<UserModel>) out.get("POC_CURSOR")).get(0);
        return userDelete;
    }

    @PostConstruct
    void init() {
        //All users
        this.getAllUsers = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("SYSTEM")
                .withCatalogName("PKG_JDBC_USER")
                .withProcedureName("SP_JDBC_USER_ALL")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("IS_SOFT", Types.INTEGER)
                )
                .returningResultSet("POC_CURSOR", BeanPropertyRowMapper.newInstance(UserModel.class));

        //One user by id
        this.getOneUserById = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("SYSTEM")
                .withCatalogName("PKG_JDBC_USER")
                .withProcedureName("SP_JDBC_USER_ONE")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("PON_ID", Types.INTEGER)
                )
                .returningResultSet("POC_CURSOR", BeanPropertyRowMapper.newInstance(UserModel.class));

        //User register
        this.postUserRegister = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("SYSTEM")
                .withCatalogName("PKG_JDBC_USER")
                .withProcedureName("SP_JDBC_USER_REGISTER")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("POV_USERNAME", Types.VARCHAR),
                        new SqlParameter("POV_PASSWORD", Types.CHAR),
                        new SqlParameter("POV_FIRSTNAME", Types.VARCHAR),
                        new SqlParameter("POV_LASTNAME", Types.VARCHAR),
                        new SqlParameter("POV_COUNTRY", Types.VARCHAR)
                )
                .returningResultSet("POC_CURSOR", BeanPropertyRowMapper.newInstance(UserModel.class));

        //Delete user by id
        this.delUserById = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("SYSTEM")
                .withCatalogName("PKG_JDBC_USER")
                .withProcedureName("SP_JDBC_USER_DELETE")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("PON_ID", Types.INTEGER)
                )
                .returningResultSet("POC_CURSOR", BeanPropertyRowMapper.newInstance(UserModel.class));
    }
}
