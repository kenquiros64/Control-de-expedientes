class TelefonosController < ApplicationController
  before_action :authenticate_medico!
  # DELETE /telefonos/1
  # DELETE /telefonos/1.json
  def destroy
    @telefono = Telefono.find(params[:id])
    @telefono.destroy
    respond_to do |format|
      format.js { render :layout => false }
    end
  end
end
