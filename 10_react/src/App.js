import './App.css';
import React, {useState} from "react";


const initialValues = {
    id: '',
    name: '',
    author: '',
    genre: ''

}


function App() {

    const [bookData, setBookData] = useState(initialValues);
    const [books, setBooks] = useState([])
    const [editableBookData, setEditableBookData] = useState({
        isEdit: false,
        bookId: null
    })


    const isFilledFields = bookData.name && bookData.author && bookData.genre

    const handleSubmitBook = (event) => {
        event.preventDefault();
        if (isFilledFields) {
            if (editableBookData.isEdit) {
                const editedBooks = books;
                editedBooks.splice(editableBookData.bookId, 1, bookData);
                setBooks(editedBooks);
                setEditableBookData({
                    isEdit: false,
                    bookId: null
                });
            } else {
                setBooks(prevState => [...prevState, bookData]);
            }
            setBookData(initialValues)
        }
    };

    const handleCleanClick = () => setBookData(initialValues);

    const handleRemoveClick = (id) => {
        setBooks(books.filter((book, bookId) => bookId !== id));
    }

    const handleEditClick = (book, id) => {
        setBookData(book)
        setEditableBookData({
            isEdit: true,
            bookId: id
        });
    };
    
    
    
    return (
        <div className={"wrapper"}>
            <div className={"wrapper-content"}>
                <div className={"table-data"}>
                    <table>
                        <tbody>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Author</th>
                                <th>Genre</th>
                                <th>Actions</th>
                            </tr>
                            {
                                books.map((book, id) => (
                                    <tr key={id}>
                                        {/*<td>{book.id}</td>*/}
                                        <td>{id}</td>
                                        <td>{book.name}</td>
                                        <td>{book.author}</td>
                                        <td>{book.genre}</td>
                                        <td>
                                            <div>
                                                <button onClick={() => handleEditClick(book, id)} className={"edit-action"}>Edit</button>
                                                <button onClick={() => handleRemoveClick(id)} className={"delete-remove"}>Remove</button>
                                            </div>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>

                <div>
                    <form onSubmit={handleSubmitBook} onReset={handleCleanClick}>
                        <input placeholder="Write Book Name" onChange={event => setBookData(prevState => ({
                            ...prevState,
                            name: event.target.value
                        }))} value={bookData.name}
                        />
                        <input placeholder="Write Book Author" onChange={event => setBookData(prevState => ({
                            ...prevState,
                            author: event.target.value
                        }))} value={bookData.author}/>
                        <input placeholder="Write Book Genre" onChange={event => setBookData(prevState => ({
                            ...prevState,
                            genre: event.target.value
                        }))} value={bookData.genre}/>
                        <div className={"buttons-wrapper"}>
                            <button type={"reset"}>Clean</button>
                            <button disabled={!isFilledFields} type={"submit"}>{editableBookData.isEdit ? 'Update' : 'Save'}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default App;
