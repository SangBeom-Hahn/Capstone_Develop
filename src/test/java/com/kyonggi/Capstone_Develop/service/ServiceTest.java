package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.repository.*;
import com.kyonggi.Capstone_Develop.service.situation.InterimService;
import com.kyonggi.Capstone_Develop.service.situation.ProposalService;
import com.kyonggi.Capstone_Develop.service.situation.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public abstract class ServiceTest {
    @Autowired
    protected NoticeBoardService noticeBoardService;
    
    @Autowired
    protected StudentRepository studentRepository;
    
    @Autowired
    protected NoticeBoardRepository noticeBoardRepository;
    
    @Autowired
    protected AuthService authService;
    
    @Autowired
    protected PasswordEncoder passwordEncoder;
    
    @Autowired
    protected RefreshTokenRepository refreshTokenRepository;
    
    @Autowired
    protected StudentService studentService;
    
    @Autowired
    protected CommentRepository commentRepository;
    
    @Autowired
    protected CommentService commentService;
    
    @Autowired
    protected SituationService situationService;
    
    @Autowired
    protected GraduationRepository graduationRepository;
    
    @Autowired
    protected ApplyRepository applyRepository;
    
    @Autowired
    protected SubmitService submitService;
    
    @Autowired
    protected ProposalService proposalService;
    
    @Autowired
    protected ProposalRepository proposalRepository;
    
    @Autowired
    protected InterimService interimService;
    
    @Autowired
    protected InterimRepository interimRepository;
}
