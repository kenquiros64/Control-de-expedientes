class EmailsController < ApplicationController
  before_action :authenticate_medico!
  # DELETE /email/1
  # DELETE /email/1.json
  def destroy
    @email = Email.find(params[:id])
    @email.destroy
    respond_to do |format|
      format.js { render :layout => false }
    end
  end
end
