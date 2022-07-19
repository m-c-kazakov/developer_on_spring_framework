import './App.css';
import React, {useState} from "react";
import CustomInput from "../CustomInput/CustomInput";


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

    const handleInputChange = (event, fieldName) => {
        setBookData(prevState => ({
            ...prevState,
            [fieldName]: event.target.value
        }))
    }


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
                                            <button onClick={() => handleEditClick(book, id)}
                                                    className={"edit-action"}>Edit
                                            </button>
                                            <button onClick={() => handleRemoveClick(id)}
                                                    className={"delete-remove"}>Remove
                                            </button>
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
                        <CustomInput
                            placeholder={"Write Book Name"}
                            handleChange={handleInputChange}
                            value={bookData.name}
                            fieldName={"name"}
                        />
                        <CustomInput
                            placeholder={"Write Book Author"}
                            handleChange={handleInputChange}
                            value={bookData.author}
                            fieldName={"author"}
                        />
                        <CustomInput
                            placeholder={"Write Book Genre"}
                            handleChange={handleInputChange}
                            value={bookData.genre}
                            fieldName={"genre"}
                        />
                        <div className={"buttons-wrapper"}>
                            <button type={"reset"}>Clean</button>
                            <button disabled={!isFilledFields}
                                    type={"submit"}>{editableBookData.isEdit ? 'Update' : 'Save'}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default App;
