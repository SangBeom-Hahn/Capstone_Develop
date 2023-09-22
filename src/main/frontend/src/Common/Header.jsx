import { useNavigate, Link } from 'react-router-dom';
import React from 'react';

const Header = () => {
  const navigate = useNavigate();

  const handleHomeClick = () => {
    navigate('/api/home');
  };

  const linkStyles = {
      color: 'white',
      fontWeight: 'bold'
  };

  return (
    <div className="header position-sticky border-3 border-top border-primary bg-dark">
      {/* navigation start */}
      <div className="container">
        <nav className="navbar navbar-expand-lg navbar-default">
          <a className="navbar-brand" onClick={handleHomeClick}>
            <img src="/img/cspop_logo.png" width="110" height="40" alt="" />
          </a>
          <div className="collapse navbar-collapse" id="navbar-default">
            <ul className="navbar-nav mx-auto">
              <li class="nav-item bold">
                <Link to="/api/noticeboards" className="nav-link" style={linkStyles}>
                  공지사항
                </Link>
              </li>
              <li class="nav-item bold">
                <Link to="/api/graduation/guide" className="nav-link" style={linkStyles}>
                  안내및내규
                </Link>
              </li>
              <li class="nav-item bold">
                <Link to="/api/graduation/schedule" className="nav-link" style={linkStyles}>
                  진행일정
                </Link>
              </li>
              <li class="nav-item bold">
                <Link to="/api/graduation/graduate_management" className="nav-link" style={linkStyles}>
                  졸업자조회
                </Link>
              </li>
            </ul>
          </div>
        </nav>
      </div>
      {/* navigation close */}
    </div>
  );
};

export default Header;
