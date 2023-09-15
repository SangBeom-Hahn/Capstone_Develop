import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Header from '../Header';

const NoticeUpdate = () => {
  const { id } = useParams();
  const [notice, setNotice] = useState({ title: '', content: '' });
  const accessToken = sessionStorage.getItem('accessToken');
  const navigate = useNavigate();

  const getNoticeData = async (noticeBoardId) => {
    try {
      const response = await axios.get(`/api/noticeboards/${noticeBoardId}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      setNotice(response.data);
      console.log('수정할 데이터 불러왔습니다.');
    } catch (error) {
      console.error(error);
      alert('수정할 게시물을 불러오지 못했습니다.');
    }
  }

  const handleTitleUpdate = (e) => {
    setNotice({ ...notice, title: e.target.value });
  }

  const handleContentUpdate = (e) => {
    setNotice({ ...notice, content: e.target.value });
  }

  const noticeUpdate = async () => {
    try {
      await axios.put(`/api/admins/noticeboards/${id}`, notice, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      alert('게시물이 수정되었습니다.');
      navigate(`/api/noticeboards/${id}`);
    } catch (error) {
      console.error(error);
      alert('게시물을 수정하지 못했습니다.');
    }
  }

  return (
    <div>
      <Header />
      <div className="container" style={{ fontFamily: 'Noto Sans Korean, Malgun Gothic, sans-serif' }}>
        <div className="lf-contents pd12">
          <div style={{ padding: '12px' }}>
            <div className="form-group">
              <input
                type="text"
                className="form-control"
                value={notice.title}
                onChange={handleTitleUpdate}
              />
            </div>
            <div className="form-group">
              <textarea
                className="form-control"
                value={notice.content}
                onChange={handleContentUpdate}
              />
            </div>
            <div className="text-center pd12">
              <button className="lf-button primary" onClick={noticeUpdate}>
                수정
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NoticeUpdate;
