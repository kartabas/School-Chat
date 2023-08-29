
function setFormMessage(formElement,type,message){
	const messageElement = formElement.querySelector(".form__message")

	messageElement.textContent= message;
	messageElement.classList.remove("form__message--success", "form__message--error");
	messageElement.classList.add(`form__message--${type}`);

}

function setInputError(inputElement,message){
	inputElement.classList.add("form__input--error");
	inputElement.parentElement.querySelector(".form__input-error-message").textContent = message;

	
}


function clearInputError(inputElement){
	inputElement.classList.remove("form__input--error");
	inputElement.parentElement.querySelector(".form__input-error-message").textContent = "";
}


document.addEventListener("DOMContentLoaded", () =>{
	const loginForm =document.querySelector("#login");
	const registerForm =document.querySelector("#register");


	registerForm.addEventListener("submit" , e =>{
		e.preventDefault();

		//Perform your AJAX/Fetch login

		setFormMessage(registerForm,"error" , "Invalid username/password comdination!");
	});


	document.querySelector(".user-nickname").forEach(inputElement => {

		inputElement.addEventListener("blur", e=>{

			if(e.target.id === "#username" && e.target.value.length <10 ){
				
				setInputError(inputElement,"Username must be at least 10 characters in length");
			}


		});

		inputElement.addEventListener("input" , e=>{
			clearInputError(inputElement);
		});

	});

});


