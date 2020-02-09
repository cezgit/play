import React from 'react';

export default function Filters(props) {
    return (
        <form className="bd-search d-flex align-items-center">
            <input type="search" className="form-control ds-input" id="search-input"
                placeholder="Search..." aria-label="Search for..." autocomplete="off"></input>
            <button className="btn btn-link bd-search-docs-toggle d-md-none p-0 ml-3" type="button" data-toggle="collapse" data-target="#bd-docs-nav" aria-controls="bd-docs-nav" aria-expanded="false" aria-label="Toggle docs navigation">
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 30 30" role="img" focusable="false">
                    <title>Menu</title><path stroke="currentColor" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2" d="M4 7h22M4 15h22M4 23h22" />
                </svg>
            </button>
        </form>
    );
}

