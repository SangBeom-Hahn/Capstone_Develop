import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import SignUp from './Signup';
import NoticeList from './Notice/NoticeList';
import NoticeView from './Notice/NoticeView';
import NoticeWrite from './Notice/NoticeWrite';
import NoticeUpdate from './Notice/NoticeUpdate';
import Guide from './Guide';
import Schedule from './Schedule';

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
        <Route path="/api/noticeboards" element={<NoticeList />} />
        {/* 공지사항 작성 화면 */}
        <Route path="/api/admins/noticeboards" element={<NoticeWrite />} />
        {/*공지사항 개당 화면 */}
        <Route path="/api/noticeboards/:id" element={<NoticeView />} />
        {/*공지사항 수정 화면 */}
        <Route path="/api/admins/noticeboards/:id" element={<NoticeUpdate />} />
        {/* 안내 및 내규 화면 */}
        <Route path="/api/graduation/guide" element={<Guide />} />
        {/* 진행일정 화면 */}
        <Route path="/api/graduation/schedule" element={<Schedule />} />
      </Routes>
    </Router>
  );
};

export default App;
