import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Notice.css';
import { useNavigate, useParams } from 'react-router-dom';
import Header from '.././Header';

const NoticeView = () => {
  const [notice, setNotice] = useState();
  const { id } = useParams();
  const navigate = useNavigate();
  const accessToken = sessionStorage.getItem('accessToken');

  useEffect(() => {
      getNoticeData(id);
    }, [id]);

  const getNoticeData = async (noticeBoardId) => {
      try {
        const response = await axios.get(`/api/noticeboards/${noticeBoardId}`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
        setNotice(response.data);
      } catch (error) {
        console.error('게시물을 불러오지 못했습니다.');
      }
    }

  const deleteNotice = async (noticeBoardId) => {
    const confirmed = window.confirm(
      '정말로 글을 삭제하시겠습니까?\n삭제된 글은 복구할 수 없습니다.'
    );

    if (confirmed) {
      try {
        const response = await axios.delete(`/api/admins/noticeboards/${noticeBoardId}`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
        console.log('글 삭제 성공:', response.data);
        navigate('/api/noticeboards');
        alert('글이 삭제되었습니다.');
      } catch (error) {
        console.error('글 삭제 오류:', error);
        alert('글 삭제가 실패했습니다.');
      }
    }
  };

  return (
    <>
      <Header />
      <br />
      <h2 align="center">게시글 상세정보</h2>

      <div className="post-view-wrapper">
        {
          notice ? (
            <>
              <div className="post-view-row">
                <label>게시글 번호</label>
                <label>{ notice.id }</label>
              </div>
              <div className="post-view-row">
                <label>제목</label>
                <label>{ notice.title }</label>
              </div>
              <div className="post-view-row">
                <label>작성일</label>
                <label>{ notice.createdDate.slice(0, 10) }</label>
              </div>
              <div className="post-view-row">
                <label>작성자</label>
                <label>{ notice.authorLoginId}</label>
              </div>
              <div className="post-view-row">
                <label>조회수</label>
                <label>{ notice.views }</label>
              </div>
              <div className="post-view-row">
                <label>내용</label>
                <label> { notice.content } </label>
              </div>
            </>
          ) : '게시물을 불러오는 중입니다...'
        }
        <button className="post-view-go-list-btn" onClick={() => navigate('/api/noticeboards')}>목록으로 돌아가기</button>&nbsp;
        <button className="post-view-go-list-btn" onClick={() => navigate(`/api/admins/noticeboards/${notice.id}`)}>수정</button>&nbsp;
        <button className="post-view-go-list-btn" onClick={() => deleteNotice(notice.id)}>삭제</button>
      </div>
    </>
  )
}

export default NoticeView;
