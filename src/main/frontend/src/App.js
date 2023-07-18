import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import SignUp from './Signup';

const App = () => {
  return (
    <Router>
      <Routes>
        {/* 홈 화면 */}
        <Route path="/api/home" element={<Home />} />
        {/* 로그인 화면 */}
        <Route path="/api/login" element={<Login />} />
        {/* 회원가입 화면 */}
        <Route path="/api/signup" element={<SignUp />} />
      </Routes>
    </Router>
  );
};

export default App;
