import React from 'react';

const Pagination = ({ currentPage, lastPage, totalSize, onPageChange }) => {
  const renderPageNumbers = () => {
    const pageNumbers = [];
    const startPage = currentPage - (currentPage % 10)
    const endPage = currentPage - (currentPage % 10) + 9

    for (let i = startPage; i <= endPage; i++) {
      pageNumbers.push(i);
    }

    return pageNumbers.map((number) => (
      <li
        key={number}
        className={`page-item ${currentPage === number ? 'active' : ''}`}
      >
        <button
          className="page-link"
          onClick={() => onPageChange(number)}
        >
          {number + 1}
        </button>
      </li>
    ));
  };

  return (
    <nav aria-label="Page navigation">
      <ul className="pagination justify-content-center">
        {currentPage > 10 && (
          <li className="page-item">
            <button
              className="page-link"
              onClick={() => onPageChange(currentPage - 10)}
            >
              {'<<'}
            </button>
          </li>
        )}
        {renderPageNumbers()}
        {currentPage + 10 <= lastPage && (
          <li className="page-item">
            <button
              className="page-link"
              onClick={() => onPageChange(currentPage + 10)}
            >
              {'>>'}
            </button>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Pagination;
