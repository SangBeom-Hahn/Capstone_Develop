import React from 'react';

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  const renderPageNumbers = () => {
    const pageNumbers = [];
    for (let i = 0; i < totalPages; i++) {
      pageNumbers.push(i + 1); // 페이지 번호를 1부터 시작하도록 수정
    }

    return pageNumbers.map((number) => (
      <li
        key={number}
        className={number === currentPage + 1 ? 'page-item active' : 'page-item'}
      >
        <button
          className="page-link"
          onClick={() => onPageChange(number - 1)} // 페이지 번호를 0부터 시작하도록 수정
        >
          {number}
        </button>
      </li>
    ));
  };

  return (
    <nav aria-label="Page navigation">
      <ul className="pagination justify-content-center">
        <li className={currentPage === 0 ? 'page-item disabled' : 'page-item'}>
          <button
            className="page-link"
            onClick={() => onPageChange(currentPage - 1)}
          >
            이전
          </button>
        </li>
        {renderPageNumbers()}
        <li
          className={
            currentPage === totalPages - 1 ? 'page-item disabled' : 'page-item'
          }
        >
          <button
            className="page-link"
            onClick={() => onPageChange(currentPage + 1)}
            disabled={currentPage === totalPages - 1}
          >
            다음
          </button>
        </li>
      </ul>
    </nav>
  );
};

export default Pagination;
