class Medicos::SessionsController < Devise::SessionsController
 #before_action :configure_sign_in_params, only: [:create]

  # GET /resource/sign_in
  def new
    super
  end

  # POST /resource/sign_in
  def create
    medico = Medico.find_by(email: params[:medico][:email])
    passcode = medico.passcode
    if medico
      if params[:medico][:password] == passcode
        sign_in_and_redirect medico, notice: "Iniciaste sesión correctamente."
      else
        flash[:error] =  "Contraseña incorrecta."
      end
    end
  end

  # DELETE /resource/sign_out
  # def destroy
  #   super
  # end

  # protected

  # If you have extra params to permit, append them to the sanitizer.
  # def configure_sign_in_params
  #   devise_parameter_sanitizer.permit(:sign_in, keys: [:attribute])
  # end
end
