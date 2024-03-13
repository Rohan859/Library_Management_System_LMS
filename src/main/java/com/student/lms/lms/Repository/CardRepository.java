package com.student.lms.lms.Repository;

import com.student.lms.lms.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<LibraryCard,Integer>
{

}
