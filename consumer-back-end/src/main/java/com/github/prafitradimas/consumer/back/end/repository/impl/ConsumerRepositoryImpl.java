package com.github.prafitradimas.consumer.back.end.repository.impl;

import com.github.prafitradimas.consumer.back.end.entity.ConsumerEntity;
import com.github.prafitradimas.consumer.back.end.entity.ConsumerStatusEnum;
import com.github.prafitradimas.consumer.back.end.repository.ConsumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ConsumerRepositoryImpl implements ConsumerRepository {

    @Autowired
    public DataSource dataSource;

    @Override
    public List<ConsumerEntity> findAll() {
        Connection connection;
        PreparedStatement prepStatement;
        ResultSet resultSet;
        List<ConsumerEntity> consumers = new ArrayList<>();
        String query = "SELECT * FROM consumers";

        try {
            connection = dataSource.getConnection();
            prepStatement = connection.prepareStatement(query);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                ConsumerStatusEnum consumerStatus;

                if (status.equals("active")) {
                    consumerStatus = ConsumerStatusEnum.ACTIVE;
                } else {
                    consumerStatus = ConsumerStatusEnum.INACTIVE;
                }

                var consumer = ConsumerEntity.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .address(resultSet.getString("address"))
                    .city(resultSet.getString("city"))
                    .province(resultSet.getString("province"))
                    .status(consumerStatus)
                    .register_date(resultSet.getTimestamp("register_date").toLocalDateTime())
                    .created_at(resultSet.getTimestamp("created_at").toLocalDateTime())
                    .updated_at(resultSet.getTimestamp("updated_at").toLocalDateTime())
                    .build();
                consumers.add(consumer);
            }

        } catch (SQLException e) {
            String message = String.format(
                "Level: %s, Class: %s, Method: %s, Cause: %s\n",
                "ERROR",
                this.getClass().getName(),
                "findAll()",
                e.getMessage()
            );
            log.error(message);
        }
        return consumers;
    }

    @Override
    public ConsumerEntity insert(ConsumerEntity entity) {
        ConsumerEntity consumer = null;
        Connection connection;
        PreparedStatement prepStatement;
        ResultSet resultSet;
        String sql = "INSERT INTO consumers(name,address,city,province) VALUES(?,?,?,?)";
        String query = "SELECT * FROM consumers WHERE name = ?";

        try {
            connection = dataSource.getConnection();
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, entity.getName());
            prepStatement.setString(2, entity.getAddress());
            prepStatement.setString(3, entity.getCity());
            prepStatement.setString(4, entity.getProvince());

            int result = prepStatement.executeUpdate();
            if (result > 0) {
                PreparedStatement queryStatement = connection.prepareStatement(query);
                queryStatement.setString(1, entity.getName());
                resultSet = queryStatement.executeQuery();

                if (resultSet.next()) {
                    ConsumerStatusEnum consumerStatus;
                    String status = resultSet.getString("status");

                    if (status.equals("active")) {
                        consumerStatus = ConsumerStatusEnum.ACTIVE;
                    } else {
                        consumerStatus = ConsumerStatusEnum.INACTIVE;
                    }

                    consumer = ConsumerEntity.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .address(resultSet.getString("address"))
                        .city(resultSet.getString("city"))
                        .province(resultSet.getString("province"))
                        .status(consumerStatus)
                        .register_date(resultSet.getTimestamp("register_date").toLocalDateTime())
                        .created_at(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .updated_at(resultSet.getTimestamp("updated_at").toLocalDateTime())
                        .build();

                    return consumer;
                }
            }

        } catch (SQLException e) {
            String message = String.format(
                "Level: %s, Class: %s, Method: %s, Cause: %s\n",
                "ERROR",
                this.getClass().getName(),
                "insert(ConsumerEntity entity)",
                e.getMessage()
            );
            log.error(message);
        }
        return consumer;
    }

    @Override
    public ConsumerEntity update(ConsumerEntity entity) {
        ConsumerEntity consumer = null;
        Connection connection;
        PreparedStatement prepStatement;
        ResultSet resultSet;
        String sql = "UPDATE consumers SET name = ?, address = ?, city = ?, province = ?, status = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try {
            connection = dataSource.getConnection();
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, entity.getName());
            prepStatement.setString(2, entity.getAddress());
            prepStatement.setString(3, entity.getCity());
            prepStatement.setString(4, entity.getProvince());
            prepStatement.setString(5, entity.getStatus().getStatus());
            prepStatement.setInt(6, entity.getId());

            log.info(prepStatement.toString());
            int result = prepStatement.executeUpdate();
            if (result > 0) {
                String query = "SELECT * FROM consumers WHERE id = ?";
                PreparedStatement queryStatement = connection.prepareStatement(query);
                queryStatement.setInt(1, entity.getId());
                resultSet = queryStatement.executeQuery();

                if (resultSet.next()) {
                    ConsumerStatusEnum consumerStatus;
                    String status = resultSet.getString("status");

                    if (status.equals("active")) {
                        consumerStatus = ConsumerStatusEnum.ACTIVE;
                    } else {
                        consumerStatus = ConsumerStatusEnum.INACTIVE;
                    }

                    consumer = ConsumerEntity.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .address(resultSet.getString("address"))
                        .city(resultSet.getString("city"))
                        .province(resultSet.getString("province"))
                        .status(consumerStatus)
                        .register_date(resultSet.getTimestamp("register_date").toLocalDateTime())
                        .created_at(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .updated_at(resultSet.getTimestamp("updated_at").toLocalDateTime())
                        .build();

                    return consumer;
                }
            }

        } catch (SQLException e) {
            String message = String.format(
                "Level: %s, Class: %s, Method: %s, Cause: %s\n",
                "ERROR",
                this.getClass().getName(),
                "update(ConsumerEntity entity)",
                e.getMessage()
            );
            log.error(message);
        }
        return consumer;
    }

    @Override
    public int deleteById(Integer id) {
        Connection connection;
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM consumers WHERE id = ?";
        int result = 0;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = String.format(
                "Level: %s, Class: %s, Method: %s, Cause: %s\n",
                "ERROR",
                this.getClass().getName(),
                "deleteById(Integer id)",
                e.getMessage()
            );
            log.error(message);
        }

        return result;
    }

    public Optional<ConsumerEntity> findById(Integer id) {
        Connection connection;
        PreparedStatement prepStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM consumers WHERE id = ?";

        try {
            connection = dataSource.getConnection();
            prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {

                String status = resultSet.getString("status");
                ConsumerStatusEnum consumerStatus;

                if (status.equals("active")) {
                    consumerStatus = ConsumerStatusEnum.ACTIVE;
                } else {
                    consumerStatus = ConsumerStatusEnum.INACTIVE;
                }

                var consumer = ConsumerEntity.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .address(resultSet.getString("address"))
                    .city(resultSet.getString("city"))
                    .province(resultSet.getString("province"))
                    .status(consumerStatus)
                    .register_date(resultSet.getTimestamp("register_date").toLocalDateTime())
                    .created_at(resultSet.getTimestamp("created_at").toLocalDateTime())
                    .updated_at(resultSet.getTimestamp("updated_at").toLocalDateTime())
                    .build();

                return Optional.of(consumer);
            }

        } catch (SQLException e) {
            String message = String.format(
                "Level: %s, Class: %s, Method: %s, Cause: %s\n",
                "ERROR",
                this.getClass().getName(),
                "findById(Integer id)",
                e.getMessage()
            );
            log.error(message);
        }
        return Optional.empty();
    }

}
