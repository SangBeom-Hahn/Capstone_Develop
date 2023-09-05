import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Notice.css';
import { useNavigate, useParams } from 'react-router-dom';
import Header from '.././Header';

const NoticeView = ({ location }) => {
  const [ notice, setNotice ] = useState();
  const navigate = useNavigate();
  const { id } = useParams();
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
                <label>{ notice.createDate }</label>
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
          ) : '해당 게시글을 찾을 수 없습니다.'
        }
        <button className="post-view-go-list-btn" onClick={() => navigate('/api/noticeboards')}>목록으로 돌아가기</button>&nbsp;
      </div>
    </>
  )
}

export default NoticeView;
