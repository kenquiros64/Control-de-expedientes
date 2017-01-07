class Medicos::RegistrationsController < Devise::RegistrationsController
  before_action :is_signed_in?
  skip_before_action :require_no_authentication
  before_action :configure_sign_up_params, only: [:create]
  before_action :configure_account_update_params, only: [:update]

  # GET /resource/sign_up
  #def new
  #  super
  #end

  # POST /resource
  def create
    super do
      resource.passcode = params[:medico][:password]
    end
  end

  
  # GET /resource/edit
  # def edit
  #   super
  # end

  # PUT /resource
  def update
    super do
      pass = params[:medico][:password]
    
      if params[:medico][:password].blank?
        resource.passcode = params[:medico][:current_password]
      else
        resource.passcode = params[:medico][:password]
      end
    end
  end

  # DELETE /resource
  # def destroy
  #   super
  # end

  # GET /resource/cancel
  # Forces the session data which is usually expired after sign
  # in to be expired now. This is useful if the user wants to
  # cancel oauth signing in/up in the middle of the process,
  # removing all OAuth session data.
  # def cancel
  #   super
  # end

  protected

  # If you have extra params to permit, append them to the sanitizer.
  def configure_sign_up_params
    devise_parameter_sanitizer.permit(:sign_up) {|u| u.permit(:codigo, :cedula, :nombre, :apellido1, :apellido2, :nacionalidad, :activo, :codigo, :email, :password, :password_confirmation)}
    
  end

  # If you have extra params to permit, append them to the sanitizer.
  def configure_account_update_params
    devise_parameter_sanitizer.permit(:account_update) {|u| u.permit(:codigo, :cedula, :nombre, :apellido1, :apellido2, :nacionalidad, :activo,:codigo, :email, :password, :password_confirmation, :current_password)}
  end
  

  def is_signed_in?
    if !medico_signed_in?
      redirect_to new_medico_session_path
    end
  end
  # The path used after sign up.
  def after_sign_up_path_for(resource)
    root_path
  end

  # The path used after sign up for inactive accounts.
  # def after_inactive_sign_up_path_for(resource)
  #   super(resource)
  # end
end
