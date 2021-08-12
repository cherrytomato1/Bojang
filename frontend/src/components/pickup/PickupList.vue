<template>
  <v-app class="color5">
    <v-main>
      <v-container>
        <v-row>
          <v-col
            cols="8"
          >
            <p>
              주문번호 : 123456
            </p>
          </v-col>
        </v-row>
      </v-container>
      <v-form>
        <v-container>
          <v-row>
            <v-col
              cols="3"
            >
              <h3> 민기네 수산시장 </h3>
            </v-col>
            <v-col
              cols="3"
            >
              <v-text>고등어</v-text><br>
              <v-text>갈치</v-text>
            </v-col>

            <v-col
              cols="3"
            >
              <v-text>1</v-text><br>
              <v-text>2</v-text>
            </v-col>
            <v-col
              cols="3"
            >
              <v-container>
                <v-text>21.07.21.12:00</v-text><br>
                <v-text>주문완료</v-text><br>
                <v-btn>픽업완료</v-btn>
              </v-container>
            </v-col>
            <v-container />
            <v-container>
              <v-row>
                <v-col
                  cols="8"
                >
                  <p>
                    주문번호 : 654321
                  </p>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row>
                <v-col
                  cols="3"
                >
                  <h3> 민기네 정육 </h3>
                </v-col>
                <v-col
                  cols="3"
                >
                  <v-text>삼겹살</v-text><br>
                  <v-text>목살</v-text>
                </v-col>

                <v-col
                  cols="3"
                >
                  <v-text>1</v-text><br>
                  <v-text>2</v-text>
                </v-col>
                <v-col
                  cols="3"
                >
                  <!-- 그리드 통합으로 조절하는 법 알면 수정해서 다듬기 -->
                  <v-container>
                    <v-text>21.07.21.12:00</v-text><br>
                    <v-text>주문완료</v-text><br>
                    <v-btn>픽업완료</v-btn>
                  </v-container>
                  <v-container />
                </v-col>
                <v-container />
                <v-container>
                  <v-row>
                    <v-col
                      cols="3"
                    >
                      <h3> 민기네 청과 </h3>
                    </v-col>
                    <v-col
                      cols="3"
                    >
                      <v-text>사과</v-text><br>
                      <v-text>포도</v-text>
                    </v-col>

                    <v-col
                      cols="3"
                    >
                      <v-text>1</v-text><br>
                      <v-text>2</v-text>
                    </v-col>
                  </v-row>
                </v-container>
              </v-row>
            </v-container>
          </v-row>
        </v-container>
      </v-form>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'PickupList',
}
</script>

<style>

</style>
