import React, { useState } from 'react';
import axios from 'axios';

const SignUp = () => {
    const [isCheckedId, setIsCheckedId] = useState(0);
    const [isChangedId, setIsChangedId] = useState(0);
    const [idInput, setIdInput] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [name, setName] = useState('');
    const [gender, setGender] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [major, setMajor] = useState('');
    const [questionPw, setQuestionPw] = useState('');
    const [answerPw, setAnswerPw] = useState('');


    // 학과 선택 함수
    const majorOptions = () => {
        const majorOptions = [
            '컴퓨터공학부',
            '인공지능학부',
            '안전보안전공'
        ];

        return majorOptions.map((option, index) => (
            <option key={index} value={option}>{option}</option>
        ));
    };

    // 비밀번호 찾기 질문 목록 추가 함수
    const questionOptions = () => {
        const questionOptions = [
            '기억에 남는 추억의 장소는?',
            '자신의 보물 제1호는?',
            '자신이 가장 존경하는 인물은?',
            '내가 좋아하는 캐릭터는?',
            '자신의 인생 좌우명은?'
        ];

        return questionOptions.map((option, index) => (
            <option key={index} value={option}>{option}</option>
        ));
    };

    // 아이디 유효성 검사 함수
    const validateId = () => {
        const idRegExp = /^[0-9]{9}$/g;
        // 아이디를 입력하지 않은 경우
        if (!idInput) {
            alert('아이디를 입력해주세요.');
            return false;
        }
        // 아이디는 숫자 9자리만 받기
        if (!idRegExp.test(idInput)) {
            alert('아이디가 형식에 맞지 않습니다.');
            return false;
        }

        return true;
    };

    // 비밀번호 유효성 검사 함수
    const validatePw = () => {
        const pwRegExp = /^(?=.*?[a-zA-Z])(?=.*?[#?!@$ %^&*-]).{8,40}$/;
        if (!password) {
            alert('비밀번호를 입력하세요');
            return false;
        }
        if (!pwRegExp.test(password)) {
            alert('비밀번호가 형식에 맞지 않습니다.');
            return false;
        }
        if (!confirmPassword) {
            alert('비밀번호 확인을 입력하세요');
            return false;
        }
        if (password !== confirmPassword) {
            alert('비밀번호와 비밀번호 확인이 같지 않습니다.');
            return false;
        }

        return true;
    };

    // 아이디, 비밀번호를 제외한 요소들의 유효성 검사 함수
    const validateUserInfo = () => {
        if (!name) {
            alert('이름을 입력하세요');
            return false;
        }
        // radio 선택 여부 확인
        if (!gender) {
            alert('성별을 선택해주세요.');
            return false;
        }
        if (!email) {
            alert('이메일을 입력해주세요.');
            return false;
        }
        if (!phone) {
            alert('전화번호를 입력해주세요.');
            return false;
        }
        // select option 선택 여부 확인
        if (major === '0') {
            alert('전공을 선택해주세요.');
            return false;
        }
        // 비밀번호 찾기 질문 답변 입력 여부 확인
        if (!answerPw) {
            alert('비밀번호 찾기 질문의 답변을 입력해주세요.');
            return false;
        }

        return true;
    };

    // 회원가입 함수
    const handleSignUp = (event) => {
        event.preventDefault();
        // 아이디 중복확인
        if (isCheckedId === 0 || isChangedId === 1) {
            alert('아이디 중복 확인을 해주세요');
            return false;
        }
        // 아이디 유효성 검사
        if (!validateId()) {
            return;
        }
        // 비밀번호 유효성 검사
        if (!validatePw()) {
            return;
        }
        // 성별, 이메일, 전화번호, 전공, 비밀번호 찾기 질문 답변 유효성 검사
        if (!validateUserInfo()) {
            return;
        }

        const userData = {
            studentId: idInput,
            studentPassword: password,
            confirmPassword,
            studentName: name,
            sex: gender + '자',
            birth: '2023-09-01',
            email,
            phoneNumber: phone,
            classification: '학부생',
            department: major,
            questionPw,
            answerPw,
        };

        axios
            .post('/api/user', JSON.stringify(userData), {
                headers: {
                    'Content-Type': 'application/json; charset=utf-8',
                },
            })
            .then((response) => {
                window.location.replace(response.data);
            })
            .catch((error) => {
                alert(error.response.data.errorMessage);
            });
    };

    // 아이디 중복 확인 함수
    const checkId = () => {
        // 아이디 유효성 검사
        if (!validateId()) {
            return;
        }
        // TODO: 서버와 통신하여 아이디 중복 확인 로직 추가

        setIsCheckedId(1); // 중복확인 완료됨
        setIsChangedId(0);
        alert('사용 가능한 아이디입니다.');
    };

    return (
        <div className="bg-light">
            {/* content section */}
            <div className="d-flex align-items-center position-relative min-vh-100">
                <div className="container">
                    <div className="row g-0">
                        <div className="col-md-8 col-lg-7 col-xl-6 offset-md-2 offset-lg-2 offset-xl-3 space-top-3 space-lg-0">
                            <a href="/api/home" className="d-flex justify-content-center align-items-center">
                                <img src="/img/cspop_logo.png" alt="" width="130em" />
                            </a>
                            <br />
                            {/* Form */}
                            <form onSubmit={handleSignUp} className="bg-white p-4 p-xl-6 p-xxl-8 p-lg-4 rounded-3 border" style={{ maxWidth: "500px", margin: "0 auto" }}>
                                <h1 className="mb-2 text-center h3">회원가입</h1>
                                <br />
                                <div className="mb-3">
                                    <div className="">
                                        <label htmlFor="id" className="form-label">학번</label>
                                        <span id="warningID"></span>
                                        <div className="input-group">
                                            <input
                                                type="text"
                                                className="form-control"
                                                id="id"
                                                placeholder="학번을 입력해주세요."
                                                value={idInput}
                                                onChange={(e) => setIdInput(e.target.value)}
                                                required
                                                maxLength="9"
                                            />
                                            <button className="btn btn-primary" onClick={checkId}>중복확인</button>
                                        </div>
                                    </div>
                                </div>
                                <div className="mb-3 ">
                                    <label htmlFor="password" className="form-label">비밀번호</label>
                                    <input
                                        type="password"
                                        id="password"
                                        className="form-control"
                                        placeholder="8~16자 영문 대소문자,특수문자를 사용하세요."
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className="mb-3 ">
                                    <label htmlFor="password" className="form-label">비밀번호 확인</label>
                                    <input
                                        type="password"
                                        id="confirmPassword"
                                        className="form-control"
                                        placeholder="비밀번호를 확인해주세요."
                                        value={confirmPassword}
                                        onChange={(e) => setConfirmPassword(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label">이름</label>
                                    <input
                                        type="text"
                                        id="name"
                                        className="form-control"
                                        placeholder="이름을 입력해주세요."
                                        value={name}
                                        onChange={(e) => setName(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className="md-3">
                                    <label htmlFor="gender" className="form-label">성별</label>
                                    <div id="gender">
                                        <div className="form-check">
                                            <input
                                                id="male"
                                                name="gender"
                                                type="radio"
                                                className="form-check-input"
                                                value="남"
                                                checked={gender === '남'}
                                                onChange={(e) => setGender(e.target.value)}
                                                required
                                            />
                                            <label className="form-check-label" htmlFor="male">남</label>
                                        </div>
                                        <div className="form-check">
                                            <input
                                                id="female"
                                                name="gender"
                                                type="radio"
                                                className="form-check-input"
                                                value="여"
                                                checked={gender === '여'}
                                                onChange={(e) => setGender(e.target.value)}
                                                required
                                            />
                                            <label className="form-check-label" htmlFor="female">여</label>
                                        </div>
                                    </div>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label">E-mail</label>
                                    <span id="warningEmail"></span>
                                    <div className="input-group">
                                        <input
                                            type="email"
                                            className="form-control"
                                            id="email"
                                            placeholder="E-mail을 입력해주세요."
                                            value={email}
                                            onChange={(e) => setEmail(e.target.value)}
                                            required
                                        />
                                    </div>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="phone" className="form-label">휴대폰 번호</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="phone"
                                        placeholder="휴대폰 번호를 입력해주세요."
                                        value={phone}
                                        onChange={(e) => setPhone(e.target.value)}
                                        required
                                        maxLength="13"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="major" className="form-label">학과</label>
                                    <select
                                        className="form-select"
                                        id="major"
                                        value={major}
                                        onChange={(e) => setMajor(e.target.value)}
                                        required
                                    >
                                        <option value="0">선택하세요</option>
                                        {majorOptions()}
                                    </select>
                                    <div className="invalid-feedback">
                                        학과를 선택해 주세요
                                    </div>
                                </div>
                                <div className="col-12">
                                    <label htmlFor="questionPw" className="form-label">비밀번호 찾기 질문</label>
                                    <select
                                        className="form-select"
                                        id="questionPw"
                                        value={questionPw}
                                        onChange={(e) => setQuestionPw(e.target.value)}
                                        required
                                    >
                                        <option value="">선택하세요</option>
                                        {questionOptions()}
                                    </select>
                                    <div className="invalid-feedback">
                                        비밀번호 찾기 질문을 선택하시고, 답변을 입력해주세요.
                                    </div>
                                </div>
                                <div className="col-12">
                                    <label htmlFor="answerPw" className="form-label">비밀번호 찾기 답변</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="answerPw"
                                        placeholder="답변을 입력해주세요."
                                        value={answerPw}
                                        onChange={(e) => setAnswerPw(e.target.value)}
                                        required
                                    />
                                </div>
                                <br />
                                <div className="d-grid">
                                    <button className="btn btn-primary" type="submit" id="submit-button">
                                        회원가입
                                    </button>
                                </div>
                                <div className="d-xxl-flex justify-content-end mt-4 ">
                                    <span className="text-muted font-15 mb-0 ">
                                        이미 회원이십니까? <a href="/api/login">로그인</a>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SignUp;
