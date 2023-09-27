package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.config.JpaAuditingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
@Import(JpaAuditingConfig.class)
public abstract class RepositoryTest {
    @Autowired
    protected CommentRepository commentRepository;
    
    @Autowired
    protected StudentRepository studentRepository;
    
    @Autowired
    protected NoticeBoardRepository noticeBoardRepository;
    
    @Autowired
    protected SubmitRepository submitRepository;
    
    @Autowired
    protected GraduationRepository graduationRepository;
    
    @Autowired
    protected ApplyRepository applyRepository;
    
    @Autowired
    protected ProposalRepository proposalRepository;
}
