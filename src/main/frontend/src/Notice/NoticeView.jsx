import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Notice.css';
import { useNavigate, useParams } from 'react-router-dom';
import Header from '.././Header';

const NoticeView = () => {
  const [notice, setNotice] = useState();
  const [comment, setComment] = useState('');
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

  const submitComment = async (noticeBoardId) => {
    if (comment === "" || comment.length === 0) {
      alert('댓글을 입력해주세요');
      return;
    }

    try {
      const payload = await axios.post(`/api/comments/${noticeBoardId}`, {
        content: comment,
      }, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      console.log('댓글 성공');
      setComment("");
      getNoticeData(id);
      alert('댓글 등록 완료.');
    } catch (error) {
      console.error('댓글 등록 실패', error);
      alert('댓글 등록 실패했습니다.');
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
                <label>{ notice.createdDate ? notice.createdDate.slice(0, 10) : '' }</label>
              </div>
              <div className="post-view-row">
                <label>작성자</label>
                <label>{ notice.authorLoginId }</label>
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
        <div className='Reply_div'>
          <h4> 댓글 </h4>
          <div className="Reply_write">
            <textarea
              rows='3'
              placeholder='100자 이내의 글을 입력해주세요.'
              maxLength='100'
              name='write_reply'
              value={comment}
              onChange={(e) => setComment(e.target.value)}
            />
            <button onClick={() => submitComment(notice.id)} id="reply_submit_button">등록</button>
          </div>
        </div>
        <div className="Reply_list">
          {notice && notice.comments
            ? <div>
                <h5> {notice.comments.length} 개의 댓글이 있습니다. </h5>

                <div className='reply_list_div'>
                  {notice.comments.map( (el) => {
                    return(
                      <div className='reply_list_gird'>
                        <div> {el.studentLoginId.includes("admin") ? "관리자" : el.studentLoginId} </div>
                        <div> {el.content} </div>
                        <div> {el.createdDate} </div>
                      </div>
                    )
                  })}
                </div>
              </div>
            : <h5>작성된 댓글이 없습니다.</h5>}
        </div>
      </div>
    </>
  )
}

export default NoticeView;
