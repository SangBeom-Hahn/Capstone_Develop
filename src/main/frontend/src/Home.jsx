import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Home = () => {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const accessToken = sessionStorage.getItem('accessToken');
  const refreshToken = sessionStorage.getItem('refreshToken');

  useEffect(() => {
    if (accessToken) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, [accessToken]);

  const handleHome = () => {
       navigate('/api/home');
     };
  const handleLogin = () => {
     navigate('/api/Login');
   };
   const handleLogout = async () => {
     try {
       await axios.post('/api/logout', { refreshToken });
       sessionStorage.removeItem('accessToken');
       sessionStorage.removeItem('refreshToken');
       setIsLoggedIn(false);
       alert('로그아웃 되었습니다.');
       navigate('/api/home');
     } catch (error) {
       console.error('Error during logout:', error);
       }
   };
  const handleNotice = () => {
    if (isLoggedIn) {
      navigate('/api/noticeboards');
    } else {
        alert('로그인이 필요합니다.');
    }
  };
  const handleKutis = () => {
      window.location.href = 'https://kutis.kyonggi.ac.kr/webkutis/view/indexWeb.jsp';
   };
  const handleLMS = () => {
    window.location.href = 'https://lms.kyonggi.ac.kr/login.php';
  };
  const handleKGU = () => {
      window.location.href = 'https://www.kyonggi.ac.kr/www/index.do';
   };
   const handleAIHOME = () => {
       window.location.href = 'http://cs.kyonggi.ac.kr:8080/Index';
   };
   const handleSWUNIV = () => {
       window.location.href = 'https://swuniv.kyonggi.ac.kr/';
    };

  return (
    <>
      {/* header */}
      <div className="header position-sticky border-3 border-top border-primary bg-dark">
        {/* navigation start */}
        <div className="container">
          <nav className="navbar navbar-expand-lg navbar-default">
            <a className="navbar-brand" onClick={handleHome}>
              <img src="/img/cspop_logo.png" width="110" height="40" alt="" />
            </a>
            <ul className="navbar-nav ms-auto me-lg-3">
            </ul>
            <div className="header-btn">
              {isLoggedIn ? (
                <button className="btn btn-primary" type="button" onClick={handleLogout} id="submit-button">
              	  Logout
              	</button>
              ) : (
                <button className="btn btn-primary" type="button" onClick={handleLogin} id="submit-button">
                  Login
                </button>
              )}
            </div>
          </nav>
        </div>
        {/* navigation close */}
      </div>

      {/* pageheader section */}
      <div className="bg-shape bg-dark">
        <div className="container">
          <div className="row">
            <div className="col-xl-12 col-lg-12 col-md-12 col-12"></div>
          </div>
        </div>
      </div>

      {/* dashboard nav */}
      <div className="py-12">
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-lg-8 col-md-12 col-12">
              &nbsp;
              <h1 className="fw-bold mb-3">경기대학교 졸업 시스템</h1>
              <p className="mb-4">
                당신의 졸업 요건을 확인해 보세요.
              </p>
              <button className="btn btn-success" onClick={handleNotice}>
                Graduation Start
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* footer */}
      <div className="footer pt-11 pb-3 bg-dark text-body">
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleKutis}>
                <h4 className="mb-4 text-white">Kutis</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleLMS}>
                <h4 className="mb-4 text-white">LMS</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleKGU}>
                <h4 className="mb-4 text-white">KGU</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleAIHOME}>
                <h4 className="mb-4 text-white">AI-HOME</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleSWUNIV}>
                <h4 className="mb-4 text-white">SW-UNIV</h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
