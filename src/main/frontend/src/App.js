import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import SignUp from './Signup';
import Notice from './Notice';
import Guide from './Guide';
import Schedule from './Schedule';
import GraduationList from './GraduationList';

import './App.css';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/api/home" />} />
        {/* 홈 화면 */}
        <Route path="/api/home" element={<Home />} />
        {/* 로그인 화면 */}
        <Route path="/api/login" element={<Login />} />
        {/* 회원가입 화면 */}
        <Route path="/api/Signup" element={<SignUp />} />
        {/* 공지사항 화면 */}
        <Route path="/api/notice" element={<Notice />} />
        {/* 안내 및 내규 화면 */}
        <Route path="/api/graduation/guide" element={<Guide />} />
        {/* 진행일정 화면 */}
        <Route path="/api/graduation/schedule" element={<Schedule />} />
        {/* 졸업자 조회 화면 */}
        <Route path="/api/graduation/graduate_management" element={<GraduationList />} />
      </Routes>
    </Router>
  );
};

export default App;
