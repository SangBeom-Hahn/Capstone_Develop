import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import CommonEditor from '.././Component/CommonEditor';
import Header from '.././Header';

const NoticeWrite = () => {
  const [title, setTitle] = useState('');
  const [contents, setContents] = useState('');
  const [accessToken, setAccessToken] = useState('');
  const [loggedInUserId, setLoggedInUserId] = useState('');
  const currentDate = new Date().toLocaleDateString();
  const navigate = useNavigate();

  useEffect(() => {
    const user = sessionStorage.getItem('userId');
    if (user) {
      setLoggedInUserId(user);
    }
  }, []);

  useEffect(() => {
    const token = sessionStorage.getItem('accessToken');
    setAccessToken(token);
  }, []);

  async function noticeWrite() {
    try {
      const views = 1;
      const cleanedContents = contents.replace(/<p>/g, '').replace(/<\/p>/g, '');
      const payload = await axios.post('/api/admins/noticeboards', {
        title: title,
        content: cleanedContents,
        fix: false,
        views: views,
      }, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        }
      });
      if (payload.data && payload.data.id) {
        const newNoticeId = payload.data.id;
        console.log('게시물:'+ newNoticeId + ' 저장 완료');
        alert('저장이 완료되었습니다.');
        navigate('/api/noticeboards');
      } else {
        console.log('게시물 저장 실패');
        alert('게시물을 저장하는 도중 오류가 발생하였습니다.');
      }
    } catch (error) {
      console.error(error);
      alert('게시물을 저장하는 도중 오류가 발생하였습니다.');
    }
  }

  function onEditorChange(value) {
    setContents(value);
  }

  return (
    <div>
      <Header />
      <div className="container" style={{ fontFamily: 'Noto Sans Korean, Malgun Gothic, sans-serif' }}>
        <div className="lf-contents pd12">
          <div style={{ padding: '12px' }}>
            <div className="form-group">
              <input type="text" placeholder="제목" className="form-control" onChange={(event) => setTitle(event.target.value)} />
            </div>
            <CommonEditor value={contents} onChange={onEditorChange} />
            <div className="text-center pd12">
              <button className="lf-button primary" onClick={noticeWrite}>
                저장
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NoticeWrite;
