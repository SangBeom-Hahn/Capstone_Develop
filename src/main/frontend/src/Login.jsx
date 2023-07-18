import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const Login = () => {
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        try {
            const account = {
                loginId: id,
                loginPassword: password
            };

            const response = await axios.post('/api/login/auth', account);
            const url = response.data;

            alert('로그인 성공');
            window.location.replace(url);
        } catch (error) {
            alert(error.response.data.errorMessage);
        }
    };

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
            handleLogin();
        }
    };

    return (
        <div className="bg-light">
            <div className="d-flex align-items-center position-relative min-vh-100">
                <div className="container">
                    <div className="row g-0">
                        <div className="col-md-8 col-lg-7 col-xl-6 offset-md-2 offset-lg-2 offset-xl-3 space-top-3 space-lg-0">
                            <a href="/api/home" className="d-flex justify-content-center align-items-center">
                                <img src="/img/cspop_logo.png" alt="" width="130em" />
                            </a>
                            <br />
                            <div className="bg-white p-4 p-xl-6 p-xxl-8 p-lg-4 rounded-3 border" style={{ maxWidth: "500px", margin: "0 auto" }}>
                                <h1 className="mb-2 text-center h3">로그인</h1>
                                <br />
                                <div className="mb-3">
                                    <label htmlFor="id" className="form-label">학번</label><span id="warningID"></span>
                                    <div className="input-group">
                                        <input type="text" className="form-control" id="id" placeholder="학번을 입력해주세요." value={id} onChange={(e) => setId(e.target.value)} required maxLength="9" />
                                    </div>
                                </div>
                                <br />
                                <div className="mb-3">
                                    <label htmlFor="password" className="form-label">비밀번호</label>
                                    <input type="password" id="password" className="form-control" placeholder="비밀번호를 입력해주세요." value={password} onChange={(e) => setPassword(e.target.value)} required="" onKeyPress={handleKeyPress} />
                                </div>
                                <br />
                                <div className="d-grid">
                                    <button className="btn btn-primary" type="button" onClick={handleLogin} id="submit-button">
                                        로그인
                                    </button>
                                </div>
                                <div className="d-xxl-flex justify-content-end mt-4">
                                    <span className="text-muted font-15 mb-0">
                                        <a href="/api/signup">회원가입</a>&nbsp;
                                        <a href="/api/passwordReset">비밀번호변경</a>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;
