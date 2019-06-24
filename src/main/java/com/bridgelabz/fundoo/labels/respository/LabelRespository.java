
package com.bridgelabz.fundoo.labels.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoo.labels.model.Labels;

public interface LabelRespository extends JpaRepository<Labels,Long> {
public Optional<Labels> findByLabelName(String labelName);
public Labels findByLabelIdAndUserId(long labelid,long UserId);
public List<Labels> findByUserId(long userid);


public Optional<Labels> findByLabelId(long labelid);
}
