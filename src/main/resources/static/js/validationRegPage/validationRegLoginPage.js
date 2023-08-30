
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



		let inputValue=document.querySelector("#password").value;

		let inputValueConfirm=document.querySelector("#confirm-password").value;

		let inputElement2 =document.querySelector("#confirm-password");
		

		if( inputValueConfirm  !=inputValue ){
			
			
			setInputError(inputElement2,"Incorect password!");

		}else{
			
			clearInputError(inputElement2);
		}

		
		

		
	});

	
	

});


