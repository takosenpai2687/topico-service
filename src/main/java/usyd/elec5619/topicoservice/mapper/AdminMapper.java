package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {

    @Update("UPDATE t_user SET role = 'ROLE_ADMIN' WHERE id = #{userId}")
    void assignAdmin(Long userId);
}
