console.log("work");

function setFormMessage(formElement, type, message) {
	const messageElement = formElement.querySelector(".form__message")

	messageElement.textContent = message;
	messageElement.classList.remove("form__message--success", "form__message--error");
	messageElement.classList.add(`form__message--${type}`);

}

function setInputError(inputElement, message) {

	inputElement.classList.add("form__input--error");
	inputElement.parentElement.querySelector(".form__input-error-message").textContent = message;


}



function clearInputError(inputElement) {
	inputElement.classList.remove("form__input--error");
	inputElement.parentElement.querySelector(".form__input-error-message").textContent = "";
}







	const registerForm = document.querySelector("#register");


	//---------------------------------------------------------------------------------


	registerForm.addEventListener("submit", e => {


		const inputPasswordValue = document.querySelector("#password").value;
		const inputPassword = document.querySelector("#password");

		const inputPasswordConfirmValue = document.querySelector("#confirm-password").value;
		const inputPasswordConfirm = document.querySelector("#confirm-password");



		//Validation for Password




		if (inputPasswordConfirmValue != inputPasswordValue) {

			setInputError(inputPasswordConfirm, "Incorrect confirm-password!");
			e.preventDefault();

		} else {
			clearInputError(inputPasswordConfirm);

		}

		//Min 1 special character.
		const arrayOfSp = ["!", "@", "#", "$", "%", "&", "_", "-"];
		let specialCharacterCheck = false;


		const special = (c) => {
			return arrayOfSp.find(item => item === c)
		}

		for (let i = 0; i < inputPasswordValue.length; i++) {
			const isPresent = special(inputPasswordValue[i]);
			if (isPresent) {
				specialCharacterCheck = true;
				break;
			}
		}





		//Min 1 special character.
		if (specialCharacterCheck != true) {
			setInputError(inputPassword, "Min 1 special character");
			e.preventDefault();

		} else if (inputPasswordValue.length < 8) {
			setInputError(inputPassword, "Min 8 characters or more");
			e.preventDefault();

		} else {
			clearInputError(inputPassword);

		}


		//----------------------------------------------------------------------





	});








