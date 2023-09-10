package usyd.elec5619.topicoservice.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import usyd.elec5619.topicoservice.model.Image;
import usyd.elec5619.topicoservice.model.ImageExample;

public interface ImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    long countByExample(ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int deleteByExample(ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int insert(Image row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int insertSelective(Image row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    List<Image> selectByExample(ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    Image selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int updateByExampleSelective(@Param("row") Image row, @Param("example") ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int updateByExample(@Param("row") Image row, @Param("example") ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int updateByPrimaryKeySelective(Image row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_image
     *
     * @mbg.generated Mon Sep 11 03:49:05 AEST 2023
     */
    int updateByPrimaryKey(Image row);
}