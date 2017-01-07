class PacienteCitasController < ApplicationController
    before_action :authenticate_medico!
  before_action :set_citum, only: [:show, :edit, :update, :destroy]

  # GET /cita
  # GET /cita.json
  def index
    @paciente = Paciente.find(params[:paciente_id])
    @cita = @paciente.citum.paginate(page: params[:page], :per_page => 25)
  end

  # GET /cita/1
  # GET /cita/1.json
  def show
    @paciente = Paciente.find(params[:paciente_id])
  end

  # GET /cita/new
  def new
    @citum = Citum.new
    @paciente = Paciente.find(params[:paciente_id])
  end

  # GET /cita/1/edit
  def edit
    @citum = Citum.find(params[:id]) 
    @paciente = Paciente.find(params[:paciente_id])
    @path = { url: paciente_cita_path(@paciente, @citum), html: { method: :put } }
  end

  # POST /cita
  # POST /cita.json
  def create
    @citum = Citum.new(citum_params)
    @citum.medico = current_medico
    @paciente = Paciente.find(params[:paciente_id])
    @citum.paciente_id = @paciente.id

    respond_to do |format|
      if @citum.save
        format.html { redirect_to paciente_cita_path(@paciente, @citum), notice: 'Cita fue creada exitosamente.' }
        format.json { render :show, status: :created, location: @citum }
      else
        format.html { render :new }
        format.json { render json: @citum.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /cita/1
  # PATCH/PUT /cita/1.json
  def update
    @paciente = Paciente.find(params[:paciente_id])
    respond_to do |format|
      if @citum.update(citum_params)
        format.html { redirect_to paciente_cita_path(@paciente, @citum), notice: 'Cita fue actualizada exitosamente.' }
        format.json { render :show, status: :ok, location: @citum }
      else
        format.html { render :edit }
        format.json { render json: @citum.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /cita/1
  # DELETE /cita/1.json
  def destroy
    @citum.observacions.each do |obs|
       Observacion.delete(obs)
    end
    @citum.observacions.destroy_all
    @citum.destroy
    @paciente = Paciente.find(params[:paciente_id])
    respond_to do |format|
      format.html { redirect_to paciente_cita_path(@paciente,@citum), notice: 'Cita fue eliminada exitosamente.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_citum
      @citum = Citum.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def citum_params
      params.require(:citum).permit(:fecha, :hora)
    end
end
